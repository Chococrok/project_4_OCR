package io.ab.library.service.impl;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.soap.SOAPFaultException;

import org.springframework.stereotype.Service;

@Service
public class FaultService {

	void sendNewClientSoapFault(String msg) throws SOAPException, SOAPFaultException {
		SOAPFactory soapFactory = SOAPFactory.newInstance();
		SOAPFault soapFault = soapFactory.createFault(msg,
				new QName("http://schemas.xmlsoap.org/soap/envelope/", "Client"));
		throw new SOAPFaultException(soapFault);
	}

}
