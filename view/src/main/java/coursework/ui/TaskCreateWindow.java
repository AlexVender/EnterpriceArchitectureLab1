package coursework.ui;

import com.vaadin.data.Binder;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import coursework.dao.exceptions.DAOException;
import coursework.dao.interfaces.ProjectsDAO;
import coursework.dao.interfaces.TasksDAO;
import coursework.dao.interfaces.UsersDAO;
import coursework.datatypes.Priority;
import coursework.entities.ProjectEntity;
import coursework.entities.TaskEntity;
import coursework.entities.UserEntity;
import coursework.utils.EJBBeanProvider;
import coursework.utils.EnumItemCaptionGenerator;

import java.time.LocalDate;
import java.util.Arrays;


public class TaskCreateWindow extends Window {
    public static final float CREATE_TASK_WINDOW_WIDTH = 800;
    
    public static final String TASK_CREATED_SUCCESSFULLY_MESSAGE = "Task created successfully";
    public static final String ERROR_WHILE_CREATING_TASK_MESSAGE = "Error while creating task";
    
    private final FormLayout formLayout = new FormLayout();
    
    private final TextField taskName = new TextField("Name");
    private final TextArea taskDescription = new TextArea("Description");
    private final ComboBox<Priority> taskPriority = new ComboBox<>("Priority");
    private final DateField taskStartDate = new DateField("Start Date");
    private final ComboBox<UserEntity> taskAssignee = new ComboBox<>("Assignee");
    private final ComboBox<ProjectEntity> taskProject = new ComboBox<>("Project");
    
    private final DateField taskDueDate = new DateField("Due Date");
    private final Button btnCreate = new Button("Create");
    
    private TasksDAO tasksDAO = EJBBeanProvider.getInstance().getTasksDAO();
    private UsersDAO usersDAO = EJBBeanProvider.getInstance().getUsersDAO();
    private ProjectsDAO projectsDAO = EJBBeanProvider.getInstance().getProjectsDAO();
    
    private Binder<TaskEntity> binder = new Binder<>();
        
    public TaskCreateWindow() {
        setCaption("Create New Task");
        setWidth(CREATE_TASK_WINDOW_WIDTH, Unit.PIXELS);
        setResizable(false);
        setModal(true);
        
        setContent(formLayout);
        formLayout.setMargin(true);
        formLayout.addComponent(taskName);
        formLayout.addComponent(taskDescription);
        formLayout.addComponent(taskPriority);
        formLayout.addComponent(taskStartDate);
        formLayout.addComponent(taskDueDate);
        formLayout.addComponent(taskAssignee);
        formLayout.addComponent(taskProject);
        formLayout.addComponent(btnCreate);
        center();
        
        taskName.setSizeFull();
        taskDescription.setSizeFull();
        taskPriority.setItems(Arrays.asList(Priority.values()));
        taskPriority.setItemCaptionGenerator(new EnumItemCaptionGenerator<>());
        taskPriority.setEmptySelectionAllowed(false);
        taskStartDate.setValue(LocalDate.now());
        taskAssignee.setItemCaptionGenerator(item -> item.getFirstName() + " " + item.getLastName());
        taskAssignee.setItems(usersDAO.readAll(0, 0));
        taskProject.setItemCaptionGenerator(ProjectEntity::getName);
        taskProject.setItems(projectsDAO.readAll(0, 0));
    
        binder.setBean(new TaskEntity());
        binder.forField(taskName)
                .asRequired("Task name may not be empty")
                .withValidator(new StringLengthValidator(
                        "Task name must be at least 2 characters long", 2, null))
                .withValidator(new StringLengthValidator(
                        "Task name can not be longer than 100 characters", null, 100))
                .bind(TaskEntity::getName, TaskEntity::setName);
        binder.forField(taskDescription)
                .bind(TaskEntity::getDescription, TaskEntity::setDescription);
        binder.forField(taskPriority)
                .asRequired("Task priority may not be empty")
                .bind(TaskEntity::getPriority, TaskEntity::setPriority);
        binder.forField(taskStartDate)
                .asRequired("Task start date may not be empty")
                .withValidator(startDate -> {
                    LocalDate dueDate = taskDueDate.getValue();
                    return taskDueDate.isEmpty() || taskStartDate.isEmpty() || (startDate.compareTo(dueDate) <= 0);
                }, "Task start date can not be after the due date")
                .bind(TaskEntity::getStartDate, TaskEntity::setStartDate);
        binder.forField(taskDueDate)
                .withValidator(dueDate -> {
                    LocalDate startDate = taskStartDate.getValue();
                    return taskDueDate.isEmpty() || taskStartDate.isEmpty() || (startDate.compareTo(dueDate) <= 0);
                }, "Task due date can not be earlier than the start date")
                .bind(TaskEntity::getDueDate, TaskEntity::setDueDate);
        binder.forField(taskAssignee)
                .asRequired("Task assignee date may not be empty")
                .bind(TaskEntity::getAssignee, TaskEntity::setAssignee);
        binder.forField(taskProject)
                .asRequired("Task project date may not be empty")
                .bind(TaskEntity::getProject, TaskEntity::setProject);
    
        binder.addStatusChangeListener(event -> btnCreate.setEnabled(binder.isValid()));
        btnCreate.setEnabled(false);
        btnCreate.addClickListener(
                event -> {
                    if (binder.isValid()) {
                        try {
                            tasksDAO.create(binder.getBean());
                            Notification successNotification = new Notification(TASK_CREATED_SUCCESSFULLY_MESSAGE,
                                    Notification.Type.HUMANIZED_MESSAGE);
                            successNotification.setDelayMsec(2000);
                            successNotification.show(Page.getCurrent());
                        } catch (DAOException e) {
                            Notification failedNotification = new Notification(ERROR_WHILE_CREATING_TASK_MESSAGE,
                                    Notification.Type.WARNING_MESSAGE);
                            failedNotification.setDelayMsec(3000);
                            failedNotification.show(Page.getCurrent());
                        }
                        close();
                    }
                });
    }
}
