package org.sopt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.sopt.common.ApiResponse;
import org.sopt.common.SuccessMessage;
import org.sopt.dto.MemberCreateRequest;
import org.sopt.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Member", description = "회원 API")
@RestController
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Operation(summary = "회원 생성")
    @PostMapping
    public ResponseEntity<ApiResponse<Long>> createMember(@RequestBody MemberCreateRequest request) {
        return toResponse(SuccessMessage.MEMBER_CREATED, memberService.createMember(request));
    }

    @Operation(summary = "회원 삭제 (작성한 게시글도 함께 삭제)")
    @DeleteMapping("/{memberId}")
    public ResponseEntity<ApiResponse<Void>> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return toResponse(SuccessMessage.MEMBER_DELETED, null);
    }

    private <T> ResponseEntity<ApiResponse<T>> toResponse(SuccessMessage successMessage, T data) {
        return ResponseEntity.status(successMessage.getStatus())
                .body(ApiResponse.success(successMessage, data));
    }
}
