export interface OrderDTO {
  id?: string;
  moment?: string;
  status: string;
  customer: string;
  product: string;
  quantity: number;
  price: number;
  total?: number;
}

export interface ValidationError {
  timestamp: string;
  status: number;
  error: string;
  message: string;
  path: string;
  errors: FieldMessage[];
}

export interface FieldMessage {
  field: string;
  message: string;
}
