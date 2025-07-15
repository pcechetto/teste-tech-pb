import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { getOrderById } from '../services/orderService';
import type { OrderDTO } from '../types/Order';
import Card from '../components/Card';

const OrderDetails: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const [order, setOrder] = useState<OrderDTO | null>(null);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    if (id) {
      getOrderById(id).then(setOrder).finally(() => setLoading(false));
    }
  }, [id]);

  if (loading) return <p>Carregando detalhes...</p>;
  if (!order) return <p>Pedido não encontrado.</p>;

  return (
    <Card style={{ maxWidth: 400, margin: '2rem auto' }}>
      <h2>Detalhes do Pedido</h2>
      <p><b>ID:</b> {order.id}</p>
      <p><b>Data:</b> {order.moment && new Date(order.moment).toLocaleString()}</p>
      <p><b>Status:</b> {order.status}</p>
      <p><b>Cliente:</b> {order.customer}</p>
      <p><b>Produto:</b> {order.product}</p>
      <p><b>Quantidade:</b> {order.quantity}</p>
      <p><b>Preço unitário:</b> R$ {order.price.toFixed(2)}</p>
      <p><b>Total:</b> R$ {order.total?.toFixed(2)}</p>
      <button onClick={() => navigate(-1)}>Voltar</button>
    </Card>
  );
};

export default OrderDetails;
