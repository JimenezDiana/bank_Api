package bankApi.bank_api.entities.users;

import javax.persistence.Entity;

@Entity
public class Admin extends User{

    public Admin() {
    }

    public Admin (String username){super(username);}
    public Admin(String username, String password) {
        super(username, password);
    }
}
