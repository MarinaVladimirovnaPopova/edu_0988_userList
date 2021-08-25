package com.example.secondapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;  //1. сначала создаём переменную recyclerView
    UserAdapter userAdapter; //10. создаем адаптер
    ArrayList<User> userList  = new ArrayList<>(); //13. список из строк? потом из объектов

    Button addUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // 2. вызвался метод onCreate
        setContentView(R.layout.activity_main);
        for (int i = 0; i<100; i++){ // 14.начиняем ArrayList пользователями
            User user = new User();
            user.setUserName("Пользователь " + i);
            user.setUserLastName("Фамилия " + i);
            userList.add(user);
        }

        recyclerView = findViewById(R.id.recyclerView); //3. находим recyclerView по идентификатору (назначен в activity_main )
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this)); // 4. методом setLayoutManager устанавливаем способ отображения списка recyclerView на экране - LinearLayoutManager (линейный) для данной активности
        userAdapter = new UserAdapter(userList); //11. создаем объект от юзерадаптора и передаем его setAdapter (строкой ниже)
        recyclerView.setAdapter(userAdapter);   //5, 12 userAdapter отвечает за отображение строчек на экране

        addUser = findViewById(R.id.addUser);//находим кнопку showAnswer на активности

        /*------------------------------*/

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
                intent.putExtra("userList", userList);
                startActivity(intent);
            }
        });

        /*------------------------------*/
    }

private class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener{ //***//7. создаем ещё класс, расширяющий RecyclerView.ViewHolder: отдает каждый отдельный элемент в списке (объект-держатель представления)
    TextView itemTextView;//28.принимаем имя пользователя
    User user;
    public UserHolder(LayoutInflater inflater, ViewGroup viewGroup) {// 21. добавляем инфлятор и viewGroup
        super(inflater.inflate(R.layout.single_item, viewGroup, false)); //9. создаём конструктор 22.передаем инфлятор и вызываем метод inflate, раздуваем макет 23.макет элемента создаем в новом файле single_item (тоже создаем, в папке layout)
        itemTextView = itemView.findViewById(R.id.itemTextView); // 29.переменная, в кот.находится TextView
        itemView.setOnClickListener(this);//***
    }
    public void  bind(String userName, User user) { //27.передали строку (имя пользователя)
        this.user = user;
        itemTextView.setText(userName); // 30. выз метод, куда записываем userName
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}

private class UserAdapter extends RecyclerView.Adapter<UserHolder>{ //6. создаём класс, где задаём адаптор для расширения класса
        ArrayList<User> users; // 15. созд. переменную, также ArrayList

        public UserAdapter(ArrayList<User>users){ // 16. создаем конструктор для передачи списка пользователей, при присвоении на момент создания объекта внутреннего свойства- в итоге users будет наполнено пользователями
            this.users = users;
        }

    @Override   // 8.реализуем все методы, кот.нужно переопраделить (alt+enter)
    public UserHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this); // 19.созд. объект LayoutInflater - раздувает, наполняет разметкой каждый элемент списка для данной активности
        return new UserHolder(inflater, parent); //18. чтобы отобразить элемент на экране, созд. объект кот. возвращает объект  UserHolder. 20. передаем инфлятор

    }

    @Override
    public void onBindViewHolder(UserHolder userHolder, int position) {   //24. передаем текущий userHolder и position(тот элеиент, кот привязываем
            User user = users.get(position); //25.созд.переменню для пользователя, кот. отобразим на экране. по индексу
            String userString = user.getUserName()+"\n"+user.getUserLastName();
            userHolder.bind(userString, user); //26. метод

    }

    @Override
    public int getItemCount() { //17.метод указ. сколько элементов списка будет на экране
        return users.size();
    }
}

}