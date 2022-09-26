INSERT INTO user(first_name, last_name, email, password, balance, enabled, role)
VALUES
('Karl','Gavillot','katl@gmail.com','P77711741@' ,50000.0, 1, 'SENDER'),

('Carlen', 'Laurent', 'carlen@g.com', 'P45061284@', 3000.0, 1, 'RECEIVER'),

('Mathieu', 'Nebra', 'mathieu@gmail.com','P11111111@', 999999.0, 1, 'RECEIVER');


INSERT INTO account (iban, balance, user_id)
VALUES
('FR99 6000 1009 5300 6789 1234 001', 50000.0, 1),
('FR99 6000 1009 5300 6789 1234 002', 3000.0, 2),
('FR99 6000 1009 5300 6789 1234 003', 999999.0, 3);

INSERT INTO user_contacts(user_contact_id, user_id)
VALUES
(2,1),
(3,1),
(2,3);


INSERT INTO transaction(date, amount, description, sender_user_id, recipient_user_id)
VALUES
('2022-09-01', 100.0, 'Rembousement de frais', 1, 2),
('2022-09-09', 200.0, 'Anniverssaire', 1, 3);