# Invoice Payments System

## Overview
This is a simple Invoice Payments System implemented in Spring Boot. It supports:
- Creating invoices
- Paying invoices
- Processing overdue invoices with late fees
- Load the all the list of invoices

## Features
- **Invoice Creation**: Create invoices with amounts and due dates.
- **Payments**: Apply payments to invoices; status updates automatically.
- **Overdue Processing**: Handles overdue invoices with customizable late fees.
- **View Invoice**: Load the all the list of invoices

## Assumptions
- Payments can be partial.
- Overdue invoices generate new invoices with remaining balances and late fees.
- Persistence is implemented in-memory for now (easily replaceable with a database).

## Exception.
- If the Invoice is not found for specific user then "Invoice not found" system will throw custom exception.