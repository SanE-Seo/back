package com.seoultech.sanEseo.like;

public record GetLikeResponse(
    String postId,
    int likeCnt
) { }
