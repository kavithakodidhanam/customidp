import React, { Component } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import Select from "react-select";

import CommandService from "../services/command-service";
import authHeader from "../services/auth-header";

const required = value => {
  if (!value ) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

const federationList = [
  {label:"OIDC" , value:"OIDC"},
  {label:"SHIB" , value:"SHIB"}
]



export default class AddIdpUser extends Component {

 
  constructor(props) {
    super(props);
    this.handleCreateIdpUser = this.handleCreateIdpUser.bind(this);
    this.onChangeProviderId = this.onChangeProviderId.bind(this);
    this.onChangeTargetId = this.onChangeTargetId.bind(this);
    this.onChangeFederation = this.onChangeFederation.bind(this);
    this.onChangeIdpId = this.onChangeIdpId.bind(this);
    this.onChangeEduPersonId = this.onChangeEduPersonId.bind(this);

    
    this.state = {
      providerId: "",
      targetId: "",
      federation: "",
      idpId: "",
      eduPersonId: "",
      loading: false,
      message: "",
      successMessage: "",
      idpList:[],
      items : []
    };
  }
  
   IdpDropDown(props) {
     
    const [loading, setLoading] = React.useState(true);
    const [items, setItems] = React.useState([]);
    const [value, setValue] = React.useState("3");
    
    React.useEffect(() => {
      let unmounted = false;
      async function getCharacters() {
        const response = await fetch(
          `http://localhost:8087/query/getAllIdps` , { headers: authHeader() }
        //  'http://ec2-100-27-22-147.compute-1.amazonaws.com:8087/query/getAllIdps' , { headers: authHeader() }
          
        );
        const body = await response.json();
        console.log(body);
        if (!unmounted) {
          setItems(
            body.map(({ idpId,idpName }) => ({ label: idpName, value: idpId }))
          );
          setLoading(false);
        }
      }
      getCharacters();
      return () => {
        unmounted = true;
      };
    }, []);
  
    return (
      <Select
        disabled={loading}
        value={value}
        options={items}
        onChange={props.onChange}
      > 
      </Select>
    );
  }
  
   
   
  onChangeProviderId(e) {
    this.setState({
      providerId: e.target.value
    });
  }

  onChangeTargetId(e) {
    this.setState({
      targetId: e.target.value
    });
  }

  onChangeFederation(e) {
    this.setState({
      federation: e.value
    });
  }

  onChangeIdpId(e) {
    this.setState({
      idpId: e.value
    });
   
  }

  onChangeEduPersonId(e) {
    this.setState({
      eduPersonId: e.target.value
    });
  }
  

  handleCreateIdpUser(e) {
    e.preventDefault();

    this.setState({
      message: "",
      loading: true,
      successMessage:""
    });

    this.form.validateAll();

    if (this.checkBtn.context._errors.length === 0) {
      
      CommandService.createIdpUser(this.state.providerId, this.state.targetId,
        this.state.federation,this.state.idpId,this.state.eduPersonId)
        .then(
        () => {
          //this.props.history.push("/addIdp");
          this.setState({
            loading: false,
            successMessage: 'Idp User created successfully'
          });
         
          
        },
        error => {
          const resMessage =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();

          this.setState({
            loading: false,
            message: resMessage,
            successMessage:""
          });
        }
      );
    } else {
      this.setState({
        loading: false
      });
    }
  }

  render() {
    
    return (
     
      <div className="col-md-12">
        <h2>Add Identity User</h2>

        <div className="form form-container">
          <Form
            onSubmit={this.handleCreateIdpUser}
            ref={c => {
              this.form = c;
            }}
          >
            {this.state.successMessage && (
              <div className="form-group">
                <div className="alert" role="alert">
                <b>  {this.state.successMessage} </b>
                </div>
              </div>
            )}
            <div className="form-group">
              <label htmlFor="providerId">ProviderId</label>
              <Input
                type="text"
                className="form-control"
                name="providerId"
                value={this.state.providerId}
                onChange={this.onChangeProviderId}
                validations={[required]}
              />
            </div>

            <div className="form-group">
              <label htmlFor="targetId">TargetId</label>
              <Input
                type="text"
                className="form-control"
                name="targetId"
                value={this.state.targetId}
                onChange={this.onChangeTargetId}
                validations={[required]}
              />
            </div>

            <div className="form-group">
              <label htmlFor="federation">Federation</label>
              <Select options={federationList}  onChange={this.onChangeFederation}/>
            </div>

            <div className="form-group">
              <label htmlFor="idpId">Idp</label>
              <this.IdpDropDown  onChange={this.onChangeIdpId}></this.IdpDropDown>
            </div>
            
            <div className="form-group">
              <label htmlFor="eduPersonId">EduPerson</label>
              <Input
                type="text"
                className="form-control"
                name="eduPersonId"
                value={this.state.eduPersonId}
                onChange={this.onChangeEduPersonId}
                validations={[required]}
              />
            </div>

            <div className="form-group">
              <button
                className="btn btn-primary btn-block"
                disabled={this.state.loading}
              >
                {this.state.loading && (
                  <span className="spinner-border spinner-border-sm"></span>
                )}
                <span>Add Idp User</span>
              </button>
            </div>

            {this.state.message && (
              <div className="form-group">
                <div className="alert alert-danger" role="alert">
                  {this.state.message}
                </div>
              </div>
            )}
            
            <CheckButton
              style={{ display: "none" }}
              ref={c => {
                this.checkBtn = c;
              }}
            />
          </Form>
        </div>
      </div>
    );
  }
}

