package com.codeElevate.blogServer.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveResult {
    private boolean error;
    private String filename;
    private String link;
}
