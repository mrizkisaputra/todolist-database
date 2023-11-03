package mrizkisaputra.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TodoList {
    private Integer todoId;
    @NonNull private String todo;
    private LocalDateTime createdAt;
}