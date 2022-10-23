package bankApi.bank_api.entities.users;

import javax.persistence.Entity;

@Entity
public class ThirdParty extends User {

    private String hashKey;

    public ThirdParty(String name, String hashKey) {
        super(name);
        this.hashKey = hashKey;
    }

    public ThirdParty(String hashKey) {
        this.hashKey = hashKey;
    }

    public ThirdParty() {
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }
}
