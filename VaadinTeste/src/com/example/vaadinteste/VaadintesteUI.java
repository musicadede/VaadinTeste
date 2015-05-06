package com.example.vaadinteste;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("vaadinteste")
public class VaadintesteUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = VaadintesteUI.class)
	public static class Servlet extends VaadinServlet {
	}

	int cont = 1;
	
	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);
		
		Pessoa pessoa = new Pessoa();
		BeanItem<Pessoa> item = new BeanItem<Pessoa>(pessoa);
		
	
		TextField nome = new TextField("Nome",item.getItemProperty("nome"));
		nome.setImmediate(true);
		
		TextField idade = new TextField("idade", item.getItemProperty("idade"));
		idade.setImmediate(true);
		
		VerticalLayout vltab = new VerticalLayout();
		
		Table tabela = new Table("Tabela de Pessoas");
//		tabela.setWidth("250px");
		tabela.addContainerProperty("Nome", String.class, null);
		tabela.addContainerProperty("idade", String.class, null);
		tabela.setImmediate(true);
		vltab.addComponent(tabela);
		tabela.setPageLength(tabela.size());
		tabela.setSelectable(true);
		tabela.setEditable(true);
		
		

		Button listar = new Button("Listar");
				
				
			listar.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				vltab.removeComponent(tabela);
				tabela.addItem(new Object[]{pessoa.getNome(),pessoa.getIdade()},cont);
				cont++;
//				nome.setValue("");
//				idade.setValue("");
				tabela.setPageLength(tabela.size());
				vltab.addComponent(tabela);
				
			}
		});
		
		

		Button mostrar = new Button("Pessoa nome");
		mostrar.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				layout.addComponent(new Label("nome = "+pessoa.getNome()));
			}
		});
		
		
		getPage().setTitle("Pagina");
		
		layout.setSpacing(true);
		layout.addComponent(nome);
		layout.addComponent(idade);
		layout.addComponent(listar);
		layout.addComponent(mostrar);
		layout.addComponent(vltab);
	}

}