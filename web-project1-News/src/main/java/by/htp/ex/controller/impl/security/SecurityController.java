package by.htp.ex.controller.impl.security;

import jakarta.servlet.http.HttpSession;

public class SecurityController {
	
	public static boolean isPermissionRole(HttpSession session) {
		if (session != null) {
			return (boolean) session.getAttribute("permissionRole");
		}
		return false;
	}	
}
