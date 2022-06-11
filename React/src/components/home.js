import React, { Component } from "react";
import { QueryClientProvider, QueryClient } from 'react-query';

import TableWrapper from './UsersIndex';

const queryClient = new QueryClient();

export default class Home extends Component {


  render() {
    return (
      <div>
        <TableWrapper />
      </div>
    );
  }
}
