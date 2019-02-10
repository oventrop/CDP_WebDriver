package business.users;

import javax.xml.bind.annotation.XmlAttribute;

public class User {

    @XmlAttribute
    public String name;
    @XmlAttribute
    public String password;
    @XmlAttribute
    public String login;
}