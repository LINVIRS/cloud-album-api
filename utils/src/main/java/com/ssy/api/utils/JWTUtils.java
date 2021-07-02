package com.ssy.api.utils;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by D丶Cheng on 2017/5/25.
 */
public class JWTUtils {

    static Logger logger = LoggerFactory.getLogger(JWTUtils.class);
    /**
     *
     * @Author: D丶Cheng
     * @Description:  generate token
     * @Date: 2017/5/25 下午5:41
     *
     * @Param: [secret, claims]
     *
     * @Return: java.lang.String
     *
     */

    public static String generateToken(String secret, Map<String, Object> claims){

        String compactJws = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        return compactJws;
    }

    /**
     *
     * @Author: D丶Cheng
     * @Description:  get Claim
     * @Date: 2017/5/25 下午5:53
     *
     * @Param: [secret, token]
     *
     * @Return: java.util.Map
     *
     */
    public static Map getClaims(String secret, String token){

        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return claims.getBody();
        } catch (MissingClaimException e) {
            logger.error("missingClaim", e);
            // we get here if the required claim is not present
        } catch (IncorrectClaimException e) {
            logger.error("incorrectClaim", e);
            // we get here if the required claim has the wrong value
        }
        return null;
    }


}
