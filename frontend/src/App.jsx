import { Routes, Route } from "react-router-dom";
import Home from "./pages/Home/Home";
import Users from "./pages/Users/Users";
import Books from "./pages/Books/Books";
import Loan from "./pages/Loan/Loan";
import './App.css';

function App() {
  
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/users" element={<Users />} />
      <Route path="/books" element={<Books />} />
      <Route path="/loan" element={<Loan />} />
    </Routes>
  )
}

export default App
