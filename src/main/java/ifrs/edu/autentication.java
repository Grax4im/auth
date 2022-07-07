package ifrs.edu;
import java.util.Arrays;
import java.util.HashSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.Claims;
import io.smallrye.jwt.build.Jwt;

@Path("/")

public class autentication {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(User enterUser)  {
        //procura o usuario no banco e traz todas suas infos
        User user = User.findByName(enterUser.getName());
        //se nao achar o usuario retorna 404
        //ja ve se a senha bate
        if(user == null || !(user.getPassword().equals(enterUser.getPassword()))) return Response.status(404).build();

        //criacao do JWT
        String jwt = Jwt.issuer("http://localhost:8081").expiresIn(60000)
        .upn(user.getEmail())
        .groups(new HashSet<>(Arrays.asList(user.getLevel())))
        .claim(Claims.full_name, user.getName())
        .sign();
        //retorna o jwt
        return Response.ok(jwt).build();
    }
}
