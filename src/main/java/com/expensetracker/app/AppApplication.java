package com.expensetracker.app;

import com.expensetracker.models.Model;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class AppApplication extends Application {

	private ApplicationContext context;

	@Override
	public void init() throws Exception {
		context = SpringApplication.run(AppApplication.class);
	}

	@Override
	public void start(Stage stage) {
		Model.getInstance().getViewFactory().showLoginWindow();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
