import React from 'react';
import './Input.css';

type InputProps = {
  label: string;
  name: string;
  value: string | number;
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  type?: string;
  error?: string;
  min?: number;
  step?: number;
};

const Input: React.FC<InputProps> = ({ label, name, value, onChange, type = 'text', error, min, step }) => (
  <div className="input-group">
    <label htmlFor={name}>{label}</label>
    <input
      id={name}
      name={name}
      value={value}
      onChange={onChange}
      type={type}
      min={min}
      step={step}
      className={error ? 'input-error' : ''}
      autoComplete="off"
    />
    {error && <span className="error-message">{error}</span>}
  </div>
);

export default Input;
