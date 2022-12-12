package main;

import java.nio.channels.SelectableChannel;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

/* MINI GAME CÁ CƯỢC TÀI XỈU
 * Một người chơi sẽ có tài khoản. 
 * Người chơi có quyền đặt cược số tiền ít hơn hoặc bằng
 * số tiền họ đang có. 
 * Luật chơi như sau: 
 * Có 3 viên xúc xắc. Mõi viên xúc có 6 mặt có giá tị từ 1 đến 6.
 * Mỗi lần lắc sẽ ra một kết quả. 
 * Kết quả = giá trị xx1 + giá trị xx2 + giá xx3 
 * 1. Nếu tổng = 3 hoặc 18 => Cái ăn hết, người chơi thua. 
 * 2. Nếu tổng = (4->10) <-> xỉu => nếu người chơi đặt xỉu thì người chơi thắng, ngược lại thua 
 * 3. Nếu tổng = (11-17) <-> tài => nếu người chơi đặt xỉu thì người chơi thắng, ngược lại thua 
 */
public class TaiXiu {
	public static void main(String[] args) {
		double accountPlayer = 5000000;
		Scanner scanner = new Scanner(System.in);
		
		//Viet Nam
		Locale locale = new Locale("vi", "VN");
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
		
		//US
//		Locale locale = new Locale("en", "US");
//		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
		
		int Select = 0;
		
		do {
			System.out.println("-------- MỜI BẠN LỰA CHỌN --------");
			System.out.println("Nhấn phím (1) để tiếp tục chơi.");
			System.out.println("Nhấn phím (SỐ BẤT KỲ) để thoát.");
			Select = scanner.nextInt();

			if (Select == 1) {
				System.out.println("*** BẮT ĐẦU CHƠI: ");
				// Đặt cược
				System.out.println("***** Tài khoản của bạn: " + numberFormat.format(accountPlayer) + " bạn muốn cược bao nhiêu? ");
				double placeABet = 0;
				do {
					System.out.println("*** Đặt cược (0<số tiền cược<=" + numberFormat.format(accountPlayer) + ":");
					placeABet = scanner.nextDouble();
				} while (placeABet <= 0 || placeABet > accountPlayer);

				// Chọn tài xỉu
				int selectTaiXiu = 0;
				do {
					System.out.println("*** Chọn: 1<-> Tài hoặc 2 <-> Xỉu");
					selectTaiXiu = scanner.nextInt();
				} while (selectTaiXiu != 1 && selectTaiXiu != 2);

				// Tung xúc xắc
				Random xucXac1 = new Random();
				Random xucXac2 = new Random();
				Random xucXac3 = new Random();

				int value1 = xucXac1.nextInt(5) + 1;
				int value2 = xucXac2.nextInt(5) + 1;
				int value3 = xucXac3.nextInt(5) + 1;
				int add = value1 + value2 + value3;

				// Tinh toán kết quả
				System.out.println("Kết quả: " + value1 + "-" + value2 + "-" + value3);

				if (add == 3 || add == 18) {
					accountPlayer = accountPlayer - placeABet;
					System.out.println("*** Tổng là: " + add + " => Nhà cái ăn hết, bạn đã thua!");
					System.out.println("*** Tài khoản của bạn là: " + numberFormat.format(accountPlayer));
				} else if (add >= 4 && add <= 10) {
					System.out.println("*** Tổng là: " + add + " => XỈU");

					if (selectTaiXiu == 2) {
						System.out.println("*** Bạn đã thắng cược");
						accountPlayer = accountPlayer + placeABet;
						System.out.println("*** Tài khoản của bạn sau khi đặt cược là: " + numberFormat.format(accountPlayer));
					} else {
						System.out.println("*** Bạn đã thua cược");
						accountPlayer = accountPlayer - placeABet;
						System.out.println("*** Tài khoản của bạn sau khi đặt cược là: " + numberFormat.format(accountPlayer));
					}
				} else {
					System.out.println("*** Tổng là: " + add + " => TÀI");

					if (selectTaiXiu == 1) {
						System.out.println("*** Bạn đã thắng cược");
						accountPlayer = accountPlayer + placeABet;
						System.out.println("*** Tài khoản của bạn sau khi đặt cược là: " + numberFormat.format(accountPlayer));
					} else {
						System.out.println("*** Bạn đã thua cược");
						accountPlayer = accountPlayer - placeABet;
						System.out.println("*** Tài khoản của bạn  sau khi đặt cược là: " + numberFormat.format(accountPlayer));
					}
				}
			}
		} while (Select == 1);
		System.out.println("GAME ĐÃ DỪNG.");
	}
}
