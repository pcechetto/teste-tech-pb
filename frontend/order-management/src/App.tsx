import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import OrderList from './pages/OrderList';
import OrderDetails from './pages/OrderDetails';
import OrderForm from './pages/OrderForm';

const App = () => (
  <Router>
    <div className="container">
      <h1>Order Management</h1>
      <Routes>
        <Route path="/" element={<OrderList />} />
        <Route path="/pedido/:id" element={<OrderDetails />} />
        <Route path="/novo" element={<OrderForm />} />
      </Routes>
    </div>
  </Router>
);

export default App;
