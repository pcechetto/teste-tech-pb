import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Card from "../components/Card";
import Input from "../components/Input";
import { createOrder } from "../services/orderService";

const initialState = {
  customer: "",
  product: "",
  quantity: 1,
  price: 0,
  status: "PENDING",
};

type FormState = typeof initialState;

type Errors = Partial<Record<keyof FormState, string>>;

const OrderForm: React.FC = () => {
  const [form, setForm] = useState<FormState>(initialState);
  const [errors, setErrors] = useState<Errors>({});
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const validate = (): boolean => {
    const newErrors: Errors = {};
    if (!form.customer) newErrors.customer = "Informe o nome do cliente";
    if (!form.product) newErrors.product = "Informe o produto";
    if (!form.status) newErrors.status = "Informe o status";
    if (!form.quantity || form.quantity < 1)
      newErrors.quantity = "Quantidade deve ser maior que 0";
    if (!form.price || form.price < 0.01)
      newErrors.price = "Preço deve ser maior que 0";
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setForm((f) => ({
      ...f,
      [name]: name === "quantity" || name === "price" ? Number(value) : value,
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!validate()) return;
    setLoading(true);
    try {
      await createOrder(form);
      navigate("/");
    } catch {
      alert("Erro ao criar pedido.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ display: 'flex', justifyContent: 'center', marginTop: 48, marginBottom: 48 }}>
      <Card style={{
        maxWidth: 440,
        width: '100%',
        margin: '0 auto',
        padding: '2.5rem 2.2rem 2rem 2.2rem',
        borderRadius: 18,
        boxShadow: '0 4px 32px 0 rgba(0,0,0,0.10)',
        border: '1.5px solid #ececec',
        background: '#fff',
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'stretch',
      }}>
        <h1 style={{
          color: '#18181b',
          fontWeight: 800,
          fontSize: '1.7rem',
          marginBottom: 6,
          letterSpacing: 0.2,
          textAlign: 'left',
          fontFamily: 'Inter, Segoe UI, Arial, sans-serif',
        }}>Novo Pedido</h1>
        <p style={{
          color: '#555',
          fontSize: '1.05rem',
          marginBottom: '1.7rem',
          marginTop: 0,
          textAlign: 'left',
          fontFamily: 'Inter, Segoe UI, Arial, sans-serif',
        }}>
          Preencha os dados abaixo para cadastrar um novo pedido.
        </p>
        <form onSubmit={handleSubmit} autoComplete="off" style={{ width: '100%', display: 'flex', flexDirection: 'column', gap: '1.25rem', alignItems: 'stretch' }}>
          <Input
            label="Cliente"
            name="customer"
            value={form.customer}
            onChange={handleChange}
            error={errors.customer}
          />
          <Input
            label="Produto"
            name="product"
            value={form.product}
            onChange={handleChange}
            error={errors.product}
          />
          <Input
            label="Status"
            name="status"
            value={form.status}
            onChange={handleChange}
            error={errors.status}
          />
          <Input
            label="Quantidade"
            name="quantity"
            type="number"
            min={1}
            value={form.quantity}
            onChange={handleChange}
            error={errors.quantity}
          />
          <Input
            label="Preço"
            name="price"
            type="number"
            min={0.01}
            step={0.01}
            value={form.price}
            onChange={handleChange}
            error={errors.price}
          />
          <div style={{ display: 'flex', gap: 16, marginTop: 12 }}>
            <button type="submit" disabled={loading} style={{
              flex: 1,
              background: '#18181b',
              color: '#fff',
              border: 'none',
              borderRadius: 8,
              padding: '0.95rem 1.1rem',
              fontSize: '1.09rem',
              fontWeight: 700,
              cursor: loading ? 'not-allowed' : 'pointer',
              opacity: loading ? 0.7 : 1,
              transition: 'background 0.2s',
              fontFamily: 'Inter, Segoe UI, Arial, sans-serif',
              boxShadow: '0 1px 6px rgba(0,0,0,0.09)',
            }}>
              {loading ? 'Salvando...' : 'Salvar'}
            </button>
            <button
              type="button"
              onClick={() => navigate(-1)}
              style={{
                flex: 1,
                background: '#fff',
                color: '#18181b',
                border: '1.5px solid #bbb',
                borderRadius: 8,
                padding: '0.95rem 1.1rem',
                fontSize: '1.09rem',
                fontWeight: 700,
                cursor: 'pointer',
                transition: 'background 0.2s',
                fontFamily: 'Inter, Segoe UI, Arial, sans-serif',
                boxShadow: '0 1px 4px rgba(0,0,0,0.04)',
              }}
            >
              Cancelar
            </button>
          </div>
        </form>
      </Card>
    </div>
  );
};

export default OrderForm;
