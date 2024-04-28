package com.seoultech.sanEseo.like.application.service;

public record GetLikeResponse(
    Long postId,
    int likeCnt
) { }
