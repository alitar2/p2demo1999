import { useState } from 'react'
import 'bootstrap/dist/css/bootstrap.min.css'
import './App.css'
import { BrowserRouter, Form, Route, Routes } from 'react-router-dom'
import { Login } from './Components/LoginRegister/Login'
import { Register } from './Components/LoginRegister/Register'
import { Teams } from './Components/Teams/Teams'
import { Users } from './Components/Users'
import { FormControl, Nav, Navbar, NavDropdown } from 'react-bootstrap'

function App() {

  return (
    <>
      <BrowserRouter>
        <Routes>
          {/* path="" helps us set Login to first thing person sees when they open webpage. */}
          <Route path="" element={<Login />} />
          <Route path="/teams" element={<Teams />} />
          <Route path="/register" element={<Register />} />
          <Route path="/users" element={<Users />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App


