package com.example.secondapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;  //1. сначала создаём переменную recyclerView
    UserAdapter userAdapter; //10. создаем адаптер
    ArrayList<String> userList  = new ArrayList<>(); //13. список из строк


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // 2. вызвался метод onCreate
        setContentView(R.layout.activity_main);
        for (int i = 0; i<100; i++){ // 14.начиняем ArrayList пользователями
            userList.add("Пользователь " + i);
        }

        recyclerView = findViewById(R.id.recyclerView); //3. находим recyclerView по идентификатору (назначен в activity_main )
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this)); // 4. методом setLayoutManager устанавливаем способ отображения списка recyclerView на экране - LinearLayoutManager (линейный) для данной активности
        userAdapter = new UserAdapter(userList); //11. создаем объект от юзерадаптора и передаем его setAdapter (строкой ниже)
        recyclerView.setAdapter(userAdapter);   //5, 12 userAdapter отвечает за отображение строчек на экране
    }

private class UserHolder extends RecyclerView.ViewHolder{ //7. создаем ещё класс, расширяющий RecyclerView.ViewHolder: отдает каждый отдельный элемент в списке (объект-держатель представления)
    TextView itemTextView;//28.принимаем имя пользователя
    public UserHolder(LayoutInflater inflater, ViewGroup viewGroup) {// 21. добавляем инфлятор и viewGroup
        super(inflater.inflate(R.layout.single_item, viewGroup, false)); //9. создаём конструктор 22.передаем инфлятор и вызываем метод inflate, раздуваем макет 23.макет элемента создаем в новом файле single_item (тоже создаем, в папке layout)
        itemTextView = itemView.findViewById(R.id.itemTextView); // 29.переменная, в кот.находится TextView
    }
    public void  bind(String userName) { //27.передали строку (имя пользователя)
        itemTextView.setText(userName); // 30. выз метод, куда записываем userName
    }
}

private class UserAdapter extends RecyclerView.Adapter<UserHolder>{ //6. создаём класс, где задаём адаптор для расширения класса
        ArrayList<String> users; // 15. созд. переменную, также ArrayList

        public UserAdapter(ArrayList<String>users){ // 16. создаем конструктор для передачи списка пользователей, при присвоении на момент создания объекта внутреннего свойства- в итоге users будет наполнено пользователями
            this.users = users;
        }

    @Override   // 8.реализуем все методы, кот.нужно переопраделить (alt+enter)
    public UserHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this); // 19.созд. объект LayoutInflater - раздувает, наполняет разметкой каждый элемент списка для данной активности
        return new UserHolder(inflater, parent); //18. чтобы отобразить элемент на экране, созд. объект кот. возвращает объект  UserHolder. 20. передаем инфлятор

    }

    @Override
    public void onBindViewHolder(UserHolder userHolder, int position) {   //24. передаем текущий userHolder и position(тот элеиент, кот привязываем
            String user= users.get(position); //25.созд.переменню для пользователя, кот. отобразим на экране. по индексу
                    userHolder.bind(user); //26. метод

    }

    @Override
    public int getItemCount() { //17.метод указ. сколько элементов списка будет на экране
        return users.size();
    }
}
}