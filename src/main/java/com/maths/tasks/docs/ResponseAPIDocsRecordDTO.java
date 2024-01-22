package com.maths.tasks.docs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseAPIDocsRecordDTO {
    private final static String base_url = "http://localhost:8080";
    private String current_task_url;
    
}
