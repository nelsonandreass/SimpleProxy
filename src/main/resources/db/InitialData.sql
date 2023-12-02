insert into ipwhitelist values('1','SGWYESSISHOP','127.0.0.1');
insert into ipwhitelist values('2','ESPAY','127.0.0.1');

insert into entityacl values('1' , 'Y' , 'SGWYESSISHOP' , 'ESPAY' , 'InquiryTransaction');
insert into entityacl values('2' , 'Y' , 'ESPAY' , 'SGWYESSISHOP' , 'pushpayment');

insert into entity_client values('1' , 'ESPAY' , 'InquiryTransaction' , '' , 'SGWYESSISHOP' , '' , '' , '' , '');
insert into entity_client values('2' , 'SGWYESSISHOP' , 'pushpayment' , '' , 'SGWYESSISHOP' , '7bc074f97c3131d2e290a4707a54a623' , '' , '' , 'SGWYESSISHOP');

Insert into entity_server values('1' , 'Espay' , 'pushpayment' , 'https://sandbox-api.espay.id/rest/digitalpay/pushtopay');
Insert into entity_server values('2' , 'SGWYESSISHOP' , 'InquiryTransaction' , 'https://run.mocky.io/v3/7beea66f-8242-4459-af49-a04ed6498a2b');