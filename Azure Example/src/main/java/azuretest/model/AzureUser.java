package azuretest.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AzureUser implements Serializable {

    private boolean accountEnabled;
    private String displayName;
    private String mailNickname;

    // @gmackayme.onmicrosoft.com
    private String userPrincipalName;
    private PasswordProfile passwordProfile;
}
