ALTER TABLE items ADD CategoryMappingAllowed boolean;
ALTER TABLE items ADD Site varchar(4);
ALTER TABLE items ADD ListingDuration varchar(8);
ALTER TABLE items ADD ListingType varchar(20);
ALTER TABLE items ADD DispatchTimeMax int;

ALTER TABLE items ADD ShippingDetails_ShippingType varchar(50);
ALTER TABLE items ADD ShippingDetails_ShippingServiceOptions_ShippintServicePriority int;
ALTER TABLE items ADD ShippingDetails_ShippingServiceOptions_ShippintService varchar(100);
ALTER TABLE items ADD ShippingDetails_ShippingServiceOptions_ShippintServiceCost double;

ALTER TABLE items ADD ReturnPolicy_ReturnsAcceptedOption varchar(20);
ALTER TABLE items ADD ReturnPolicy_RefundOption varchar(30);
ALTER TABLE items ADD ReturnPolicy_ReturnsWithinOption varchar(7);
ALTER TABLE items ADD ReturnPolicy_Description text;
ALTER TABLE items ADD ReturnPolicy_ShippingCostPaidByOption varchar(6);

ALTER TABLE items ADD PaymentMethods varchar(30);
ALTER TABLE items ADD PayPalEmailAddress varchar(255);
