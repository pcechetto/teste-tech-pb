import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Card from "../components/Card";
import { getOrders } from "../services/orderService";
import type { OrderDTO } from "../types/Order";

const OrderList: React.FC = () => {
  const [orders, setOrders] = useState<OrderDTO[]>([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    getOrders()
      .then((data) => {
        // Ordena por 'moment' decrescente (mais recente primeiro)
        setOrders(
          [...data].sort((a, b) => {
            if (!a.moment || !b.moment) return 0;
            return new Date(b.moment).getTime() - new Date(a.moment).getTime();
          })
        );
      })
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <p>Carregando pedidos...</p>;

  return (
    <div>
      <h2>Pedidos</h2>
      <button onClick={() => navigate("/novo")}>Novo Pedido</button>
      <div style={{ display: "flex", flexWrap: "wrap", gap: "1.5rem", justifyContent: "center", alignItems: "flex-start", marginTop: "2rem" }}>
        {orders.map((order) => (
          <Card
            key={order.id}
            onClick={() => navigate(`/pedido/${order.id}`)}
            style={{ width: 320 }}
          >
            <h3 style={{ marginTop: 0 }}>{order.product}</h3>
            <p>
              <b>Cliente:</b> {order.customer}
            </p>
            <p>
              <b>Status:</b> {order.status}
            </p>
            <p>
              <b>Quantidade:</b> {order.quantity}
            </p>
            <p>
              <b>Total:</b> R$ {order.total?.toFixed(2)}
            </p>
          </Card>
        ))}
      </div>
    </div>
  );
};

export default OrderList;
