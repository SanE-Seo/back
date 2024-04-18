package com.seoultech.sanEseo.like;

public record AddLikeRequest(
        Long postId,
        Long memberId
) {
}
