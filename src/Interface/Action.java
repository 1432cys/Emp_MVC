package Interface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	public ActionFoward execute(HttpServletRequest request, HttpServletResponse response);
}
