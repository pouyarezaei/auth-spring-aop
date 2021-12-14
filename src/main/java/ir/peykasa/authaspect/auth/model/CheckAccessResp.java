package ir.peykasa.authaspect.auth.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckAccessResp {
    Boolean access = false;
}
