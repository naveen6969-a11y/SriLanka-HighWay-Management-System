package Classes;

public class AuthAdmin extends AuthServiceBase {

    @Override
    protected String getTableName() {
        return "Users";
    }
}
