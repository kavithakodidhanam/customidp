import axios from "axios";
import authHeader from './auth-header';

class CommandService {
  createIdp(idpName,institutionName) {
    return axios
      .post('http://localhost:8087/command/createIdp',{
        //"http://ec2-100-27-22-147.compute-1.amazonaws.com:8087/command/createIdp"
        idpName,
        institutionName
      }, { headers: authHeader() })
      .then(response => {
        return response.data;
      }).catch({
        function (error) {
          console.log(error);
        }
      });
  }

  createIdpUser(providerId,targetId,federation,idpId,eduPersonId) {
    return axios
      .post('http://localhost:8087/command/createUser',{
        //"http://ec2-100-27-22-147.compute-1.amazonaws.com:8087/command/createUser"
        providerId,
        targetId,
        federation,
        idpId,
        eduPersonId
      }, { headers: authHeader() })
      .then(response => {
        return response.data;
      }).catch({
        function (error) {
          console.log(error);
        }
      });
  }

  
}

export default new CommandService();
