package com.twitter.like_service.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponseDTO {

    @Builder.Default
    private boolean success=true;
    private String message;
}
