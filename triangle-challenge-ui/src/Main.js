import React from 'react';

//const serverUrl = process.env.SERVER_URL;
const serverUrl = "http://localhost:8080/api";

class Main extends React.Component {

    state = {
      sideA: '',
      sideB: '',
      sideC: '',
      errorMsg: '',
      type: '',
    }

    sendRequestToServer(sideA, sideB, sideC) {
      this.setState({
        errorMsg: '',
        type: ''
      });
      fetch(serverUrl + '/get-type?sideA=' + sideA + '&sideB=' + sideB + '&sideC=' + sideC)
        .then((response) => {
          if (response.status === 200) {
            return response.json();
          } else if (response.status === 400) {
            this.setState({ errorMsg: 'Invalid triangle' });
          } else if (response.status === 500) {
            this.setState({ errorMsg: 'Something went wrong. Try again later' });
          } else {
            return response.then(Promise.reject.bind(Promise));
          }
        })
        .then((json) => {
            this.setState({ type: json.response });
        })
        .catch((error) => {
            console.log(error);
        })
    }

    handleSubmit = (event) => {
      if (this.state.sideA.length<1 ||this.state.sideB.length<1
         ||this.state.sideC.length<1) {
           this.setState({errorMsg: 'Fill out all the fields'});
      } else {
        this.sendRequestToServer(this.state.sideA, this.state.sideB, this.state.sideC);
      }
    }

  render() {
    return (
      <div className="container">
        <form data-ts="Form">
        	<fieldset>
            <span>Please enter lengths of 3 triangle's sides</span>
        		<label>
        			<input type="number" placeholder="Side A"
              onChange={(event) => this.setState({sideA: event.target.value})} required/>
        		</label>
            <label>
        			<input type="number" placeholder="Side B"
              onChange={(event) => this.setState({sideB: event.target.value})} required/>
        		</label>
            <label>
        			<input type="number" placeholder="Side C"
              onChange={(event) => this.setState({sideC: event.target.value})} required/>
        		</label>
            {this.state.errorMsg.length>0 &&
              <dl className="ts-errors">
          			<dt>Following errors occurred:</dt>
          			<dd>{this.state.errorMsg}</dd>
  		        </dl>
            }
        	</fieldset>
          <button data-ts="Button" className="ts-primary" type="submit" onClick={this.handleSubmit}>
	           <span>Determine type</span>
          </button>
        </form>
        {this.state.type.length>0 &&
          <div data-ts="Note">
            <p>Your Triangle is {this.state.type}</p>
          </div>
        }
      </div>
    );
  }

}

export default Main;
