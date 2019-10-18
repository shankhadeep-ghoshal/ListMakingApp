package org.shankhadeepghoshal.listmakingapp.itemslistflow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
    @NonNull private String categoryName;
    private int categoryId;
}