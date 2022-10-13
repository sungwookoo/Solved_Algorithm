import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static int N; // 접수 창구의 개수
	static int M; // 정비 창구의 개수
	static int K; // 차량 정비소를 방문한 고객의 수
	static int A; // 지갑을 두고 간 고객이 이용한 접수 창구번호
	static int B; // 지갑을 두고 간 고객이 이용한 정비 창구번호
	static int[] tk; // k 번째 고객이 차량 정비소를 방문하는 시간 tk
	
	static Desk[] receptDesk; // 접수 창구 배열
	static Desk[] repairDesk; // 정비 창구 배열
	static Client[] clients; // 고객 배열
	static boolean[] done; // 고객 완료 상태 검사 배열
	
	static int ans;
	

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			receptDesk = new Desk[N + 1];
			repairDesk = new Desk[M + 1];

			tk = new int[K + 1];
			clients = new Client[K + 1];
			done = new boolean[K + 1];

			ans = 0;

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				receptDesk[i] = new Desk(0, Integer.parseInt(st.nextToken()), 0);
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				repairDesk[i] = new Desk(0, Integer.parseInt(st.nextToken()), 0);
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; i++) {
				tk[i] = Integer.parseInt(st.nextToken());
				clients[i] = new Client(i, tk[i], 0, 0, 0);
			}

			simulate();

			for (int i = 1; i <= K; i++) {
				if (clients[i].receptNo == A && clients[i].repairNo == B) {
					ans = ans + i;
				}
			}

			System.out.println("#" + tc + " " + (ans==0?-1:ans));

		}
	}

	static void simulate() {
//		N: 접수 창구의 개수
//		M: 정비 창구의 개수
//		K: 차량 정비소를 방문한 고객의 수
//		A: 지갑을 두고 간 고객이 이용한 접수 창구번호
//		B: 지갑을 두고 간 고객이 이용한 정비 창구번호
//		1. 창구, 고객번호는 1부터
//		2. 접수창구 : 고객번호 낮은 순으로 접수, 빈 창구가 여러곳인 경우 창구번호가 작은 곳으로
//		3. 정비창구 : 먼저 기다리는 고객이 우선,
//		4. 동시에 온 고객은 접수창구 번호 작은 고객우선, 빈 창구 여러곳인 경우 창구번호가 작은 곳
		
		// 접수 대기 큐
		PriorityQueue<Client> receptQ = new PriorityQueue<>(new Comparator<Client>() {
			@Override
			public int compare(Client o1, Client o2) {
				if(o1.no < o2.no) return -1;
				else return 1;
			}
		});
		
		
		// 정비 대기 큐
		PriorityQueue<Client> repairQ = new PriorityQueue<>(new Comparator<Client>() {
			@Override
			public int compare(Client o1, Client o2) {
				if(o1.rt < o2.rt) return -1;
				else if(o1.rt > o2.rt) return 1;
				else {
					if(o1.receptNo < o2.receptNo) return -1;
					else if(o1.receptNo > o2.receptNo) return 1;
				}
				return -1;
			}
		});

		int t = 0; // 시간

		while (!done[K]) { // 마지막 고객이 완료가 될때까지 반복

			// 접수대기 큐에 도착한 고객 집어 넣는다.
			for (int i = 1; i <= K; i++) {
				if (clients[i].t == t) {
					receptQ.offer(clients[i]);
				}
			}

			// 접수&정비 창구 진행시간 증가 (고객이 있다면)
			for (int i = 1; i <= N; i++) { // 접수창구
				if (receptDesk[i].no != 0)
					receptDesk[i].proc++;
			}
			for (int i = 1; i <= M; i++) { // 정비창구
				if (repairDesk[i].no != 0)
					repairDesk[i].proc++;
			}

			// 접수 완료된 고객 정비 대기열로 이동
			for (int i = 1; i <= N; i++) {
				if (receptDesk[i].proc == receptDesk[i].need) {
					clients[receptDesk[i].no].rt = t; // 접수완료 시간 갱신
					repairQ.offer(clients[receptDesk[i].no]);
					receptDesk[i].proc = 0;
					receptDesk[i].no = 0; // 창구 비어있음
				}
			}

			// 접수 창구에 고객 입장
			// 비어있는 접수창구의 개수만큼 큐에서 뺀다
			for (int i = 1; i <= N; i++) {
				if (!receptQ.isEmpty()) {
					if (receptDesk[i].no == 0) {
						Client client = receptQ.poll(); // 대기 큐에서 가장 번호 작은 고객 뺀다
						client.receptNo = i; // 고객이 거친 접수 창구 갱신
						receptDesk[i].no = client.no; // 창구 고객번호 갱신
					}
				}
			}

			// 정비 완료된 고객 퇴장
			for (int i = 1; i <= M; i++) {
				if (repairDesk[i].proc == repairDesk[i].need) {
					done[repairDesk[i].no] = true;
					repairDesk[i].proc = 0;
					repairDesk[i].no = 0; // 창구 비어있음
				}
			}

			// 정비 창구에 고객 입장

			for (int i = 1; i <= M; i++) {
				if (!repairQ.isEmpty()) {
					if (repairDesk[i].no == 0) {
						Client client = repairQ.poll(); // 대기 큐에서 가장 번호 작은 고객 뺀다
						client.repairNo = i; // 고객이 거친 접수 창구 갱신
						repairDesk[i].no = client.no; // 창구 고객번호 갱신
					}
				}
			}

			t++; // 시간 증가
		}

	}

	static class Client{
		int no, t, receptNo, repairNo, rt; 
		// 고객번호, 방문(도착)시간, 이용한 접수창구번호, 이용한 정비창구번호, 정비대기열 도착시간

		public Client(int no, int t, int receptNo, int repairNo, int rt) {
			super();
			this.no = no;
			this.t = t;
			this.receptNo = receptNo;
			this.repairNo = repairNo;
			this.rt = rt;
		}
	}

	static class Desk {
		int no, need, proc; // 고객번호, 소요시간, 경과

		public Desk(int no, int need, int proc) {
			super();
			this.no = no;
			this.need = need;
			this.proc = proc;
		}
	}
}
