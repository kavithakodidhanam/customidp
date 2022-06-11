import axios from "axios";

class AuthService {
  login(username, password) {
      return axios
        .post("http://localhost:8087/login", {
          //"http://ec2-100-27-22-147.compute-1.amazonaws.com:8087/login"
          username,
          password
        })
        .then(response => {
          if (response.data.accessToken) {
            localStorage.setItem("user", JSON.stringify(response.data));
          }
          return response.data;
        });
  }

  logout() {
    localStorage.removeItem("user");
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));;
  }
}

export default new AuthService();
