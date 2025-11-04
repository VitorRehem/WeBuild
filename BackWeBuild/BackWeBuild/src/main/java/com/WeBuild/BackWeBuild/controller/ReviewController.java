package com.WeBuild.BackWeBuild.controller;

import com.WeBuild.BackWeBuild.model.Company;
import com.WeBuild.BackWeBuild.model.Review;
import com.WeBuild.BackWeBuild.model.User;
import com.WeBuild.BackWeBuild.repository.CompanyRepository;
import com.WeBuild.BackWeBuild.repository.ReviewRepository;
import com.WeBuild.BackWeBuild.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository;
    private final UserService userService;

    public ReviewController(ReviewRepository reviewRepository, CompanyRepository companyRepository, UserService userService) {
        this.reviewRepository = reviewRepository;
        this.companyRepository = companyRepository;
        this.userService = userService;
    }

    // Funcionalidade: Submeter avaliação
    @PostMapping("/submit")
    public ResponseEntity<Review> submitReview(@RequestParam Long companyId,
                                               @RequestBody Review review,
                                               @AuthenticationPrincipal UserDetails userDetails) {
        
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        User user = (User) userService.loadUserByUsername(userDetails.getUsername());

        review.setCompany(company);
        review.setUser(user);
        review.setReviewDate(LocalDateTime.now());

        Review savedReview = reviewRepository.save(review);
        
        // Atualiza a média de avaliação da empresa
        List<Review> companyReviews = reviewRepository.findByCompany(company);
        double avgRating = companyReviews.stream().mapToInt(Review::getRating).average().orElse(0.0);
        company.setAverageRating(avgRating);
        companyRepository.save(company);

        return ResponseEntity.ok(savedReview);
    }

    // Funcionalidade: Visualizar avaliações
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<Review>> getCompanyReviews(@PathVariable Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        return ResponseEntity.ok(reviewRepository.findByCompany(company));
    }
}