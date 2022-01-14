package com.springminio.app.payload;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class FileResponse {
    String filename;
    String contentType;
    Long fileSize;
    Date createdTime;
}
