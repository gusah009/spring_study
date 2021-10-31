package org.zerock.service;


import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {org.zerock.config.RootConfig.class})
@Log4j
public class BoardServiceTests {

    @Setter(onMethod_ = { @Autowired })
    private BoardService service;

    @Test
    public void testExist() {
        log.info(service);
        assertNotNull(service);
    }

    @Test
    public void testRegister() {
        BoardVO board = new BoardVO();
        board.setTitle("New Title");
        board.setContent("New Content");
        board.setWriter("Newbie");
        service.register(board);
        log.info("생성된 게시물의 번호: " + board.getBno());
    }

    @Test
    public void testGetList() {
        service.getList().forEach(board -> log.info(board));
    }

    @Test
    public void testCRUD() {
        BoardVO board = new BoardVO();
        board.setTitle("TEST BOARD1");
        board.setContent("TEST CONTENT1");
        board.setWriter("TESTER");
        service.register(board);
        service.get(board.getBno());
        service.getList();
        board.setTitle("TEST MODIFY BOARD1");
        service.modify(board);
        service.get(board.getBno());
        service.remove(board.getBno());
        service.get(board.getBno());
    }
}