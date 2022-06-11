/// <reference types="cypress" />
const HOST_URL = 'http://localhost:8082/';
const username = 'Guest';
const password = 'guest';

describe('Dashboard', () => {
  it('view dashboard', () => {
    cy.visit(HOST_URL)
    cy.get('input[name=username]').type(username);
    cy.get('input[name=password]').type(password).type('{enter}');
    cy.get("b").should('have.text', 'Search By ProviderId Idp Users in Custom Idp ')
    cy.get("a").should('have.text', 'Custom Identity ProviderDashboardLogOut')
  })

  it('search', () => {
    cy.visit(HOST_URL)
    cy.get('input[name=username]').type(username);
    cy.get('input[name=password]').type(password).type('{enter}');
    cy.contains('2').click()
  })
})
