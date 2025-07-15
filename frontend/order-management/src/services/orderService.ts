import axios from 'axios';
import type { OrderDTO } from '../types/Order';

const API_URL = 'http://localhost:8080/api/v1/pedidos';

export const getOrders = async (): Promise<OrderDTO[]> => {
  const { data } = await axios.get<OrderDTO[]>(API_URL);
  return Array.isArray(data) ? data : [data];
};

export const getOrderById = async (id: string): Promise<OrderDTO> => {
  const { data } = await axios.get<OrderDTO>(`${API_URL}/${id}`);
  return data;
};

export const createOrder = async (order: Omit<OrderDTO, 'id' | 'moment' | 'total'>): Promise<OrderDTO> => {
  const { data } = await axios.post<OrderDTO>(API_URL, order);
  return data;
};
