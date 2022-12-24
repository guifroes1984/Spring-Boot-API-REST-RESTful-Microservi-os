package curso.api.rest.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import curso.api.rest.ApplicationContextLoad;
import curso.api.rest.model.Usuario;
import curso.api.rest.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Component
public class JWTTokenAutenticacaoService {
	
	/*Tempo de validade do Token 2 dias*/
	private static final long EXPIRATION_TIME = 172800000;
	
	/*Uma senha unica para compor a autenticação e ajudar na segurança*/
	private static final String SECRET = "SenhaExtremamenteSecreta";
	
	/*Prefixo padrão de Token*/
	private static final String TOKEN_PREFIX = "Bearer";
	
	private static final String HEADER_STRING = "Authorization";
	
	/*Gerando token de autenticação e adicionando ao cabeçalho e resposta Http*/
	public void addAuthentication(HttpServletResponse response, String username) throws IOException {
		
		/*Montagem do Token*/
		String JWT = Jwts.builder() /*Chama o gerador de Token*/
				.setSubject(username) /*Adiciona o usuario*/
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))/*Tempo de expiração*/
				.signWith(SignatureAlgorithm.HS512, SECRET).compact(); /*Compactação e algoritmos de geração de senha*/
		
		/*Junta o o token com o prefixo*/
		String token = TOKEN_PREFIX + " " + JWT; /*Bearer vai ter o token bem grande=> doahfojhfsdofdsv*/
		
		/*Adiciona o cabeçalho http*/
		response.addHeader(HEADER_STRING, token); /*Authentication: epofpfgpgfpgjpgb (Aparencia do Token)*/
		
		/*Escreve token como resposta no corpo do http*/
		response.getWriter().write("{\"Authentication\": \""+token+"\"}");
		
	}
	
	
	/*Retorna o usuário validado com Token ou caso não seja valido retorna null*/
	public Authentication getAuthentication(HttpServletRequest request) {
		
		/*Pega o token enviado no cabeçalho http*/
		String token = request.getHeader(HEADER_STRING);
		
		if (token != null) {
			
			String tokenLimpo = token.replace(TOKEN_PREFIX, "").trim();
			
			/*Faz a validação do usuário*/
			String user = Jwts.parser().setSigningKey(SECRET)/*Bearer flkgjlkgh54127842184*/
						.parseClaimsJws(tokenLimpo)/*flkgjlkgh54127842184*/
						.getBody().getSubject();/*João Silva*/
			if (user != null) {
				
				Usuario usuario = ApplicationContextLoad.getApplicationContext()
						.getBean(UsuarioRepository.class).findByUserLogin(user);
				
				if (usuario != null) {
					
					if(tokenLimpo.equalsIgnoreCase(usuario.getToken())) {
					
						return new UsernamePasswordAuthenticationToken(
								usuario.getLogin(), 
								usuario.getSenha(),
								usuario.getAuthorities());
					}
				}
				
			}
			
		}
			return null;/*Não autorizado*/
	}

}
