package br.com.caelum.livraria.log;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@SuppressWarnings("serial")
@Log
@Interceptor
public class TempoDeExecucaoInterceptor implements Serializable {
	
	@AroundInvoke
	public Object tempoDeExecucao (InvocationContext contexto) throws Exception{
		
		long tempoAntes = System.currentTimeMillis();
		Object resultado = contexto.proceed();
		long tempoDepois = System.currentTimeMillis();
		
		System.out.println("Tempo total do método " + contexto.getMethod() + ": " + (tempoDepois - tempoAntes) + "ms");
		
		return resultado;
	}

}
