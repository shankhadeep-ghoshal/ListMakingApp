package org.shankhadeepghoshal.listmakingapp.loginandsignupflow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginEntity {
    private String userId;
    private String password;
}