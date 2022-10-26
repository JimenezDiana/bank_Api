package bankApi.bank_api.entities.users;

import javax.persistence.Entity;

@Entity
public class ThirdParty extends User {

    private String hashedKey;

    public ThirdParty(String name, String hashedKey) {
        super(name);
        this.hashedKey = hashedKey;
    }

    public ThirdParty(String hashedKey) {
        this.hashedKey = hashedKey;
    }

    public ThirdParty() {
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
