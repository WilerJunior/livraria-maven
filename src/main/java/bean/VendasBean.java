package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Venda;

@Named
@ViewScoped
public class VendasBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5819768201146218206L;
	
	@Inject
	EntityManager em;

	@SuppressWarnings("unchecked")
	private List<Venda> getVendas() {

		List<Venda> vendas = em.createQuery("SELECT v from Venda v").getResultList();

		return vendas;

	}
	
	public BarChartModel getVendasModel() {	
		
		BarChartModel vendasModel = new BarChartModel();		
		vendasModel.setAnimate(true);
				
		ChartSeries vendaSeries = new ChartSeries();
		vendaSeries.setLabel("Vendas 2016");
				
		List<Venda> vendas = getVendas();
		for (Venda venda : vendas) {
			vendaSeries.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
		
		vendasModel.addSeries(vendaSeries);
		
		return vendasModel;
	}

}
