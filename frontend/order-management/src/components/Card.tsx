import React from 'react';
import './Card.css';

type CardProps = {
  children: React.ReactNode;
  onClick?: () => void;
  style?: React.CSSProperties;
};

const Card: React.FC<CardProps> = ({ children, onClick, style }) => (
  <div className="card" onClick={onClick} style={style}>
    {children}
  </div>
);

export default Card;
